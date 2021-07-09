import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";

const UserPage = () => {
  const [user, setUser] = useState({
    id: "",
    username: "",
    email: "",
    role: "",
  });

  useEffect(() => {
    fetch("http://localhost:8080/user", {
      method: "get",
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    })
      .then((res) => res.json())
      .then((res) => {
        console.log("안녕");
        console.log(res);
        setUser(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <h1>유저 정보 상세보기</h1>
      <Card border="primary" style={{ width: "18rem" }}>
        <Card.Header>번호 : {user.id}</Card.Header>
        <Card.Body>
          <Card.Title>{user.username}</Card.Title>
          <Card.Text>{user.email}</Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};

export default UserPage;
