package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.domain.base.BaseTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "status = 'ACTIVE'") // ACTIVE 상태인 데이터만 조회
@Table(name="users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String userPicture;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    @OneToMany(mappedBy = "user")
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<RecentSearch> recentSearchList = new ArrayList<>();

    @OneToMany(mappedBy = "sellUser")
    private List<Product> sellProductList = new ArrayList<>();

    @OneToMany(mappedBy = "buyUser")
    private List<Product> buyProductList = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "wish_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> wishProductList;

    @OneToMany(mappedBy = "user")
    private List<ChatJoin> chatJoinList;

    public void changeInfo(String nickName, String department, String password) {
        this.nickName = nickName;
        this.department = department;
        this.password = password;
    }

    public User(String nickname, String email, String password, String department) {
       this.nickName = nickname;
       this.email = email;
       this.password = password;
       this.department = department;
    }

}
