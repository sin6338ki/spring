package com.smhrd.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.boot.model.AndMember;

@Repository
public interface MemberRepository extends JpaRepository<AndMember, Integer> {
									//<테이블과 매핑할 Object, 기본키의 type>
	//insert와 같은 기본 기능은 jpa가 이미 가지고 있으므로 정의하지 않아도 됨
}
