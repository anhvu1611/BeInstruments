package com.intrument.saleintrument.admin.service;

import com.intrument.saleintrument.web.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendRegistrationConfirmation(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Đăng Ký Thành Công");
        message.setText("Chào " + username + ",\n\nBạn đã đăng ký tài khoản Hippo Shop thành công !\n\nTrân trọng,\nĐội ngũ");
        emailSender.send(message);
    }

    public void sendOrderConfirmation(String to, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Xác Nhận Đặt Hàng");
        message.setText(String.format("Kính gửi %s,\n\nĐơn hàng của bạn với mã số %d đã được đặt thành công.\nTổng số tiền: %.2f VNĐ\nĐịa chỉ giao hàng: %s\n\nCảm ơn bạn đã mua sắm tại cửa hàng của chúng tôi!\n\nTrân trọng,\nĐội ngũ cửa hàng",
                order.getUser().getFullName(), order.getId(), order.getTotal(), order.getAddress()));
        emailSender.send(message);
    }
}

