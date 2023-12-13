import "./App.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import SignIn from "./pages/sign-in";
import Dashboard from "./pages/dashboard";
import ElectionStart from "./pages/electionStart";
import ElectionContent from "./pages/electionContent";
import QuizResults from "./pages/quizResults";
import VotersList from "./pages/votersList";
import Elections from "./pages/elections";
import ElectionVoters from "./pages/electionVoters";

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
      path: "/electionStart",
      element: <ElectionStart />,
    },
    {
      path: "/electionContent",
      element: <ElectionContent />,
    },
    {
      path: "/quizResults",
      element: <QuizResults />,
    },
    {
      path: "/elections",
      element: <Elections />,
    },
    {
      path: "/electionVoters",
      element: <ElectionVoters />,
    },
  ]);
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
