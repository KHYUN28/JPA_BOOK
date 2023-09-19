package org.example.entity;

public class MemberProxy extends Member {

    private Member target; // Member 객체를 참조할 변수

    // 생성자를 통해 Member 객체를 초기화합니다.
    public MemberProxy(Member member) {
        this.target = member;
    }

    public String getName() {
        // Member 객체가 null이 아닌 경우에만 해당 메서드를 호출하도록 수정합니다.
        if (target == null) {
            return target.getUsername();
        } else {
            // target이 null인 경우, 적절한 예외를 던지거나 기본값을 반환하도록 처리할 수 있습니다.
            return "No member available";
        }
    }
}

