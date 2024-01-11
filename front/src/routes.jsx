import { Home, SignIn, SignUp, Vote } from "@/pages";

export const routes = [
  {
    name: "home",
    path: "/home",
    element: <Home />,
    showInNavbar: true,
  },
  {
    name: "Sign In",
    path: "/sign-in",
    element: <SignIn />,
    showInNavbar: true,
  },
  {
    name: "Sign Up",
    path: "/sign-up",
    element: <SignUp />,
    showInNavbar: true,
  },
  {
    name: "Vote",
    path: "/vote",
    element: <Vote />,
    showInNavbar: false, 
  },
];

export default routes;