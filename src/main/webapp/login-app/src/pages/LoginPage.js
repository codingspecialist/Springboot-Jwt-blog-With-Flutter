import React, { useState } from "react";
import { Form, Button, Container } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { userLogin } from "../store";

const LoginPage = (props) => {
  const dispatcher = useDispatch();

  const [loginUser, setLoginUser] = useState({
    username: "",
    password: "",
  });

  const login = (e) => {
    console.log("로그인동작", e);
    e.preventDefault(); // summit 액션을 차단!
    fetch("http://localhost:3000/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(loginUser),
    })
      .then((res) => {
        //console.log(res.headers.get("Authorization"));
        let jwtToken = res.headers.get("Authorization");
        localStorage.setItem("Authorization", jwtToken);
        return res.json();
      })
      .then((res) => {
        if (res.code === 1) {
          console.log("통신 성공");
          // sessionStorage.setItem("principal", res.data); 세션으로 정보관리
          dispatcher(userLogin(res.data)); // 리덕스로 세션정보 관리
          //console.log(res.data);
          props.history.push("/");
          // 화면 동기화
        } else {
          console.log("통신 실패");
        }
      });
  };

  const changeUsername = (e) => {
    setLoginUser({
      username: e.target.value,
      password: loginUser.password,
    });
  };

  const changePassword = (e) => {
    setLoginUser({
      username: loginUser.username,
      password: e.target.value,
    });
  };

  console.log(loginUser);
  return (
    <Container>
      <Form>
        <Form.Group>
          <Form.Label>Username</Form.Label>
          <Form.Control
            name="username"
            type="text"
            placeholder="Enter username"
            onChange={changeUsername}
          />
        </Form.Group>

        <Form.Group>
          <Form.Label>Password</Form.Label>
          <Form.Control
            name="password"
            type="password"
            placeholder="Enter Password"
            onChange={changePassword}
          />
        </Form.Group>

        <Button variant="primary" type="submit" onClick={login}>
          로그인
        </Button>
      </Form>
    </Container>
  );
};

export default LoginPage;
