import React from "react";
import { useSelector } from "react-redux";

// 리엑트 클래스 컴포넌트 기반 (X)
// 리엑트 hooks => 함수형 기반 (O) => useXX 함수를 사용 가능!!
const HomePage = () => {
  const { user } = useSelector((store) => store);
  console.log(1, user);
  return (
    <div>
      <h1>Home Page</h1>
      <h3>접속한 유저 이름 : {user.username} </h3>
      <hr />
    </div>
  );
};

export default HomePage;
