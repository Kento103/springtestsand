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
    /**
     * id(自動採番)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // startgy = GenerationType.IDENTITY自動採番する
    @Getter // (lombok)getterを自動生成　get(変数)で呼び出せる　例）getid
    @Setter //(lombok)setterを自動生成 set(変数)で呼び出せる 例）setid
    private int id;

    @Column(nullable = false) // nullable(nullを許可するかの設定)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false, unique = true) // unique(ユニーク[一意]でないと登録できないようにする)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    // ゲッターセッター！！！は上に書いてるよ！ @Getter @Setter

}
