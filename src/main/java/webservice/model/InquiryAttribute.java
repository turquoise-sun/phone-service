package webservice.model;

import javax.persistence.*;

/**
 * Inquiry attribute entity
 */

@Entity
@Table(name = "INQUIRY_ATTRIBUTE")
public class InquiryAttribute {

    @Id
    @GeneratedValue
    @Column(name="ATTRIBUTE_ID")
    private Long attributeId;


    @Column(name="NAME")
    private String name;

    @Column(name="VALUE")
    private String value;


    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
