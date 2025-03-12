package com.kento.springtest.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kento.springtest.model.User;


/*
 * Repositoryのインターフェースを作成
 * Spring Data JPAのJpaRepositoryを継承することで、自動的にCRUD処理を実装できる。
 * 
 * JpaRepositoryが提供するメゾット(デフォで使える)有能！
 * findAll() 全件取得する
 * findById([データ型] [テーブルの名前]) 例）findById(int id)
 * save(User user) 新規登録または更新する
 * count() 全部の件数を取得する
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // JpaRepository<[対象となるエンティティ], [その主キーの型を指定する]> 例）JpaRepository<User Integer>
    Optional<User> findByEmail(String email); // カスタムメゾット、メールアドレスで検索(旧メゾット依存性確保のため、変更しない)

    //Optional<User> findByUsername(String username); //ユーザーネームから検索する使わない

    //Optional<User> findByEmail2(String email); // メールアドレスから検索する（使わない

}
