import "./App.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import SignIn from "./pages/sign-in";
import Dashboard from "./pages/dashboard";
import QuizStart from "./pages/quizStart";
import QuizContent from "./pages/quizContent";
import QuizResults from "./pages/quizResults";
import VotersList from "./pages/votersList";
import Elections from "./pages/elections";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <SignIn />,
    },
    {
      path: "/dashboard",
      element: <Dashboard />,
    },
    {
      path: "/votersList",
      element: <VotersList />,
    },
    {
      path: "/quizStart",
      element: <QuizStart />,
    },
    {
      path: "/quizContent",
      element: <QuizContent />,
    },
    {
      path: "/quizResults",
      element: <QuizResults />,
    },
    {
      path: "/elections",
      element: <Elections />,
    },
  ]);
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
