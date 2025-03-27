package edu.icet.mos.service.impl;

import edu.icet.mos.dto.Order;
import edu.icet.mos.dto.OrderDetail;
import edu.icet.mos.entity.CustomerEntity;
import edu.icet.mos.entity.ItemEntity;
import edu.icet.mos.entity.OrderDetailEntity;
import edu.icet.mos.entity.OrderEntity;
import edu.icet.mos.repository.CustomerRepository;
import edu.icet.mos.repository.ItemRepository;
import edu.icet.mos.repository.OrderRepository;
import edu.icet.mos.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper mapper;


    @Transactional
    @Override
    public void placeOrder(Order order) {

//        orderRepository.save(OrderEntity.builder().id(order.getId()).date(order.getDate()).time(order
//                .getTime()).amount(order.getAmount()).customer(customerRepository.findById(order.getCustomerId()).orElseThrow(() -> new RuntimeException("customer not found"))).orderDetailList(order.getOrderDetailList().stream().map(obj ->
//      OrderDetailEntity.builder()
//        ).toList()).build());

        OrderEntity orderEntity = new OrderEntity();
        CustomerEntity customer = customerRepository.findById(order.getCustomerId()).orElseThrow(() -> new RuntimeException("customer now found"));
        orderEntity.setTime(order.getTime());
        orderEntity.setDate(order.getDate());
        orderEntity.setAmount(order.getAmount());
        orderEntity.setCustomer(customer);

        List<OrderDetailEntity> detailEntities = new ArrayList<>();
        List<OrderDetail> orderDetailList = order.getOrderDetailList();


        for (OrderDetail detail : orderDetailList) {
            ItemEntity itemEntity = itemRepository.findById(detail.getItemId()).orElseThrow(() -> new RuntimeException("item not found"));

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setItem(itemEntity);
            orderDetailEntity.setQty(detail.getQty());
            orderDetailEntity.setAmount(detail.getAmount());
            orderDetailEntity.setOrder(orderEntity);

            detailEntities.add(orderDetailEntity);
        }

        orderEntity.setOrderDetailList(detailEntities);
        orderRepository.save(orderEntity);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll().stream().map(orderEntity ->
                Order.builder().date(orderEntity.getDate()).id(orderEntity.getId()).amount(orderEntity.getAmount()).time(orderEntity.getTime()).customerId(orderEntity.getCustomer().getId()).orderDetailList(orderEntity.getOrderDetailList().stream().map(o -> mapper.map(o, OrderDetail.class)).toList()).build()).toList();
    }


}
