package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.Inquiry;
import webservice.repository.InquiryRepository;

import java.util.Collection;
import java.util.List;

/**
 * Inquiry Service implementation
 */

@Service
public class InquiryServiceBean implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public Collection<Inquiry> findAll() {
        Collection<Inquiry> inquiries = inquiryRepository.findAll();
        return inquiries;
    }

    @Override
    public Inquiry findOne(Long id) {
        Inquiry inquiry = inquiryRepository.findOne(id);
        return inquiry;
    }

    @Override
    public List<Inquiry> findByCustomerName(String customerName) {
        List<Inquiry> inquiry = inquiryRepository.findByCustomerName(customerName);
        return inquiry;
    }

    @Override
    public Inquiry findByCustomerNameAndInquiryId(String customerName, Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findByCustomerNameAndInquiryId(customerName, inquiryId);
        return inquiry;
    }

    @Override
    public Inquiry create(Inquiry inquiry) {
        if (inquiry.getInquiryId() != null) {
            //Unable to create inquiry with specified ID
            return null;
        }
        Inquiry createdInquiry = inquiryRepository.save(inquiry);
        return createdInquiry;
    }

    @Override
    public Inquiry update(Inquiry inquiry) {
        Inquiry persistedInquiry = inquiryRepository.findOne(inquiry.getInquiryId());
        if (persistedInquiry == null) {
            //Unable to update requested inquiry
            return null;
        }
        persistedInquiry = inquiryRepository.save(inquiry);
        return persistedInquiry;
    }

    @Override
    public void delete(Long id) {
        inquiryRepository.delete(id);
    }
}
