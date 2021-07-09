import React from "react";
import { Form, Nav, Navbar, FormControl, Button } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { userLogout } from "../store";

const Header = () => {
  const { isLogin } = useSelector((store) => store);
  const dispatcher = useDispatch();
  const logout = () => {
    dispatcher(userLogout());
    localStorage.removeItem("Authorization");
  };

  return (
    <>
      <Navbar bg="primary" variant="dark">
        <Link to="/" className="navbar-brand">
          LoginApp
        </Link>
        <Nav className="mr-auto">
          <Link to="/" className="nav-link">
            Home
          </Link>
          <Link to="/user" className="nav-link">
            User
          </Link>
          {isLogin === false ? (
            <Link to="/login" className="nav-link">
              Login
            </Link>
          ) : (
            <div className="nav-link" onClick={logout}>
              Logout
            </div>
          )}
        </Nav>
        <Form inline>
          <FormControl type="text" placeholder="Search" className="mr-sm-2" />
          <Button variant="outline-light">Search</Button>
        </Form>
      </Navbar>
      <br />
    </>
  );
};

export default Header;
