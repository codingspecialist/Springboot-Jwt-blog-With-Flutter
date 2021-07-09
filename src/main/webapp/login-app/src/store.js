const LOGIN = "LOGIN";
const LOGOUT = "LOGOUT";

export const userLogin = (user) => {
  return {
    type: LOGIN,
    payload: user,
  };
};

export const userLogout = () => {
  return {
    type: LOGOUT,
  };
};

const initstate = {
  isLogin: false,
  user: {
    id: "",
    username: "",
    email: "",
    role: "",
  },
};

const reducer = (state = initstate, action) => {
  switch (action.type) {
    case LOGIN:
      return { isLogin: true, user: action.payload };
    case LOGOUT:
      return {
        isLogin: false,
        user: {
          id: "",
          username: "",
          email: "",
          role: "",
        },
      };
    default:
      return state;
  }
};

export default reducer;
