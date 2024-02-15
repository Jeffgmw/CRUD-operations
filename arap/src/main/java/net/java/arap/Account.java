package net.java.arap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String emailId;
    @Column
    private Long nationalId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String postedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime postedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character postedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character modifiedFlag = 'N';

}
