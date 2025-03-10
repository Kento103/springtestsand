package com.kento.springtest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity データベースのテーブルに対応するエンティティを作成する。
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番する
    @Getter
    @Setter
    private int id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    // ゲッターセッター！！！は上に書いてるよ！ @Getter @Setter

}
