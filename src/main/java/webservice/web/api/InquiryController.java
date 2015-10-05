package webservice.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.model.Inquiry;
import webservice.service.InquiryService;

import java.util.Collection;

/**
 * Inquiry controller
 */

@RestController
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;


    @RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Inquiry>> getInquiriesByCustomer(@PathVariable("customerName") String customerName) {
        Collection<Inquiry> inquiries = inquiryService.findByCustomerName(customerName);
        return new ResponseEntity<Collection<Inquiry>>(inquiries, HttpStatus.OK);
    }

    @RequestMapping(value =  "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inquiry> getInquiryByCustomerNameAndId(@PathVariable("customerName") String customerName,
                                                             @PathVariable("inquiryId") Long inquiryId) {
        Inquiry inquiry = inquiryService.findByCustomerNameAndInquiryId(customerName, inquiryId);
        if (inquiry == null) {
            return new ResponseEntity<Inquiry>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Inquiry>(inquiry, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inquiry> createInquiry(@PathVariable("customerName") String customerName, @RequestBody Inquiry inquiry) {
        inquiry.setCustomerName(customerName);
        Inquiry createdInquiry = inquiryService.create(inquiry);
        if (createdInquiry == null) {
            return new ResponseEntity<Inquiry>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Inquiry>(createdInquiry, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable("customerName") String customerName,
                                                 @PathVariable("inquiryId") Long inquiryId, @RequestBody Inquiry inquiry) {
        Inquiry persistedInquiry = inquiryService.findByCustomerNameAndInquiryId(customerName, inquiryId);
        if (persistedInquiry == null) {
            return new ResponseEntity<Inquiry>(HttpStatus.BAD_REQUEST);
        }
        inquiry.setCustomerName(customerName);
        inquiry.setInquiryId(inquiryId);
        persistedInquiry = inquiryService.update(inquiry);
        return new ResponseEntity<Inquiry>(persistedInquiry, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inquiry> deleteInquiry(@PathVariable("customerName") String customerName,
                                                 @PathVariable("inquiryId") Long inquiryId) {
        Inquiry persistedInquiry = inquiryService.findByCustomerNameAndInquiryId(customerName, inquiryId);
        if (persistedInquiry != null) {
            inquiryService.delete(inquiryId);
            return new ResponseEntity<Inquiry>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Inquiry>(HttpStatus.BAD_REQUEST);
    }

}
