package webservice.service;

import webservice.model.Inquiry;

import java.util.Collection;
import java.util.List;

/**
 * Inquiry Service interface
 */
public interface InquiryService {

    Collection<Inquiry> findAll();

    Inquiry findOne(Long id);

    List<Inquiry> findByCustomerName(String customerName);

    Inquiry findByCustomerNameAndInquiryId(String customerName, Long id);

    Inquiry create(Inquiry inquiry);

    Inquiry update(Inquiry inquiry);

    void delete(Long id);



}
