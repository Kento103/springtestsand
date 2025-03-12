package com.kento.springtest.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * Entity データベースのテーブルに対応するエンティティを作成する。
 */
@Entity
@Table(name = "users") //テーブル名を設定する為のアノテーション
@Data // クラスに付与することで、全フィールドでゲッターセッターが使えるようになる
public class User implements UserDetails {
    @Id // DBの主キーに設定する
    @GeneratedValue(strategy = GenerationType.IDENTITY) // startgy = GenerationType.IDENTITY自動採番する
    private int id; // id(主キー)

    @Column(nullable = false) // nullable(nullを許可するかの設定)
    private String username; // ユーザー名

    @Column(nullable = false, unique = true) // unique(ユニーク[一意]でないと登録できないようにする)
    private String email; // メールアドレス

    @Column(nullable = false)
    private String password; // パスワード

    private String role; // 権限設定

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
