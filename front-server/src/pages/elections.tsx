import { Link } from "react-router-dom";
import LayoutDashboard from "../layouts/layout-dashboard";

export default function Elections() {
  return (
    <LayoutDashboard>
      <div id="home">
        <div className="lg:flex justify-between items-center mb-6">
          <p className="text-2xl font-semibold mb-2 lg:mb-0">
            Elecetions List
          </p>
          <Link to={'/quizStart'}>
            <button className="bg-blue-500 hover:bg-blue-600 focus:outline-none rounded-lg px-6 py-2 text-white font-semibold shadow">
              Create an election
            </button>
          </Link>
        </div>
      </div>
      <div className="overflow-x-auto">
        <table className="table">
          {/* head */}
          <thead>
            <tr>
              <th>ID</th>
              <th>election Name</th>
              <th>Number of voters</th>
              <th>Number of condadate</th>
              <th>status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {/* row 1 */}
            <tr>
              <th>1</th>
              <td>delegue de classe</td>
              <td>20</td>
              <td>2</td>
              <td>active</td>
              <td>
                <button className="btn btn-circle btn-outline mr-2">
                <svg className="w-6 h-6 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 18">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 1v16M1 9h16"/>
                </svg>
                </button>
                <Link to={"/electionResults"}>
                  <button className="btn btn-circle btn-outline mr-2" title="Results">
                    <svg className="w-6 h-6 text-gray-800 stroke-blue-300 fill-none" xmlns="http://www.w3.org/2000/svg" width="50px" height="50px" viewBox="0 -0.5 25 25">
                      <path stroke-linejoin="round" stroke-linecap="round" stroke-width="1.5" d="M15.17 11.053L11.18 15.315C10.8416 15.6932 10.3599 15.9119 9.85236 15.9178C9.34487 15.9237 8.85821 15.7162 8.51104 15.346C7.74412 14.5454 7.757 13.2788 8.54004 12.494L13.899 6.763C14.4902 6.10491 15.3315 5.72677 16.2161 5.72163C17.1006 5.71649 17.9463 6.08482 18.545 6.736C19.8222 8.14736 19.8131 10.2995 18.524 11.7L12.842 17.771C12.0334 18.5827 10.9265 19.0261 9.78113 18.9971C8.63575 18.9682 7.55268 18.4695 6.78604 17.618C5.0337 15.6414 5.07705 12.6549 6.88604 10.73L12.253 5"></path>
                    </svg>
                </button>
                </Link>
              </td>
            </tr>
           
          </tbody>
        </table>
      </div>
    </LayoutDashboard>
  );
}