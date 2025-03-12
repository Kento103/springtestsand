package com.kento.springtest.model;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Entity データベースのテーブルに対応するエンティティを作成する。
 */
@Entity
@Table(name = "users") //テーブル名を設定する為のアノテーション
@NoArgsConstructor // デフォルトで引数なしのコンストラクタを自動生成する
@AllArgsConstructor // すべてのフィールドを引数に受け取るコンストラクタを自動生成できる
@Data // クラスに付与することで、全フィールドでゲッターセッターが使えるようになる
public class User implements UserDetails {
    @Id // DBの主キーに設定する
    @GeneratedValue(strategy = GenerationType.IDENTITY) // startgy = GenerationType.IDENTITY自動採番する
    private int id; // id(主キー)

    @Column(nullable = false) // nullable(nullを許可するかの設定)
    private String username; // ユーザー名(ユーザー名はusernameという名前にすること！)

    @Column(nullable = false, unique = true) // unique(ユニーク[一意]でないと登録できないようにする)
    private String email; // メールアドレス

    @Column(nullable = false)
    private String password; // パスワード

    private String role; // 権限設定(権限設定は　ROLE_[権限で設定すること] 例）ROLE_USER、ROLE_ADMIN)

    /* ぬるぽ　ガッ
     * ほんとはメンバ変数に書きたいけど、Springdata JPAの仕様で直接記載が出来ない。そのため、コンストラクタに以下のように記載してぬるぽを行う。
     * 参考： https://qiita.com/komatsuh/items/30bcdd7e4037005e0d4d
     */
    public User(Optional<Integer> id, Optional<String> username, Optional<String> email, String password) {
        this.id = id.orElseGet(() -> null); // nullを許容する物はこの構文で記述する
        this.email = email.orElseGet(() -> null); // nullが入る可能性のあるものはこの構文で記述する
        this.password = password; // nullを許容しないものはこれでOK
    }

    // 上で設定したものに加え、これも必要
    // nullが入る可能性のあるもの
    public Optional<Integer> getId() {
        return Optional.ofNullable(id);
    }

    // nullが入る可能性のあるもの
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    // nullを許容しないものはこれでOK
    public String getPassword() {
        return password;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    // アカウントの削除確認
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないかBAN等の処理に用いる(falseでロック)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ゲッターセッター！！！は上に書いてるよ！ @Getter @Setter

}
