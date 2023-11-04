package com.car.rental.service.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.rental.service.dao.Refund;
import com.car.rental.service.repositories.RefundRepo;
import com.car.rental.service.exceptions.GenericExceptions;
import com.car.rental.service.services.RefundService;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RefundServiceImpl implements RefundService {
    public final RefundRepo refundRepo;

    @Override
    public Refund create(Refund refund) {
        refundRepo.save(refund);
        return refund;
    }

    @Override
    public Refund update(Refund refund) {
        if (refund.getRefundId() == null) {
            throw GenericExceptions.idIsNull();
        } else {
            Refund existingRefund = this.findById(refund.getRefundId());
            if (refund.getEmployee() != null)
                existingRefund.setEmployee(refund.getEmployee());
            if (refund.getDateOfReturn() != null)
                existingRefund.setDateOfReturn(refund.getDateOfReturn());
            if (refund.getRefund() != null)
                existingRefund.setRefund(refund.getRefund());
            if (refund.getSurcharge() != null)
                existingRefund.setSurcharge(refund.getSurcharge());
            if (refund.getComments() != null)
                existingRefund.setComments(refund.getComments());

            refundRepo.save(existingRefund);
            return existingRefund;
        }
    }

    @Override
    public Refund findById(Long id) {
        return refundRepo.findById(id).orElseThrow(() -> GenericExceptions.notFound(id));
    }

    @Override
    public List<Refund> findAll() {
        return refundRepo.findAll();
    }

    @Override
    public String delete(Long id) {
        refundRepo.deleteById(id);
        return String.format("Refund with ID %d deleted", id);
    }

}
