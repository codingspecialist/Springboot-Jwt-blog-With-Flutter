import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import { useSelector } from "react-redux";

const UserPage = () => {

  const principal = useSelector(state => state.user);

  const [user, setUser] = useState({
    id: "",
    username: "",
    email: ""
  });



  useEffect(() => {


    fetch(`http://localhost:3000/user/${principal.id}`, {
      method: "get",
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    })
      .then((res) => res.json())
      .then((res) => {
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
