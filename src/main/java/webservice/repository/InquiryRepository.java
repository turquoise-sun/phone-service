package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.model.Inquiry;

import java.util.List;

/**
 * Inquiry Repository interface
 */

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findByCustomerName(String customerName);

    Inquiry findByCustomerNameAndInquiryId(String customerName, Long inquiryId);

}
