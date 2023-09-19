package jpabook.start;

// 필요한 패키지를 임포트합니다.
import javax.persistence.*;
import java.util.Date;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity // JPA 엔티티임을 나타냅니다.
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"} )})
public class Member {

    @Id // 이 필드가 엔티티의 주요 식별자 (Primary Key)임을 나타냅니다.
    @Column(name = "ID") // 데이터베이스 컬럼의 이름을 "ID"로 지정합니다.
    private String id; // 회원 ID

    @Column(name = "NAME", nullable = false, length = 10) // "NAME" 컬럼을 나타내며, null 값을 허용하지 않으며 최대 길이가 10자로 제한됩니다.
    private String username; // 회원 이름

    private Integer age; // 회원 나이

    // 추가된 부분:
    @Enumerated(EnumType.STRING) // 열거형 값을 문자열로 저장하도록 설정합니다.
    private RoleType roleType; // 회원 역할

    @Temporal(TemporalType.TIMESTAMP) // 날짜 및 시간 필드의 데이터 유형을 타임스탬프로 설정합니다.
    private Date createdDate; // 회원 생성일자

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate; // 최종 수정일자

    @Lob // 큰 텍스트 필드를 나타냅니다. 대용량 데이터를 저장하는 데 사용됩니다. CLOB
    private String description;

    @Transient // 데이터베이스에 저장되지 않는 임시 필드를 나타냅니다.
    private String temp;


    // Getter, Setter 메서드

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}



//import javax.persistence.*;  //**
//import java.util.Date;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
//@Entity
//@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint( //추가 //**
//        name = "NAME_AGE_UNIQUE",
//        columnNames = {"NAME", "AGE"} )})
//public class Member {
//
//    @Id
//    @Column(name = "ID")
//    private String id;
//
//    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
////    @Column(name = "NAME") //추가 //**
//    private String username;
//
//    private Integer age;
//
//    //=== 추가
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate; // 최종수정된 시간.
//
//    @Lob
//    private String description;
//
//    @Transient
//    private String temp;
//
//
//    //Getter, Setter
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getTemp() {
//        return temp;
//    }
//
//    public void setTemp(String temp) {
//        this.temp = temp;
//    }
//}
