import LayoutDashboard from "../layouts/layout-dashboard";

export default function VotersList() {
  return (
    <LayoutDashboard>
      <div id="home">
        <div className="lg:flex justify-between items-center mb-6">
          <p className="text-2xl font-semibold mb-2 lg:mb-0">
            Voters List
          </p>
        </div>
      </div>
      <div className="overflow-x-auto">
        <table className="table">
          {/* head */}
          <thead>
            <tr>
              <th>ID</th>
              <th>Full Name</th>
              <th>Phone Number</th>
              <th>Email</th>
              <th>CIN</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {/* row 1 */}
            <tr>
              <th>1</th>
              <td>Zamame Ayoub</td>
              <td>+212667994871</td>
              <td>zamame.ayoub@gmail.com</td>
              <td>Y493385</td>
              <td>
                <button className="btn btn-circle btn-outline mr-2">
                <svg className="w-6 h-6 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 18">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 1v16M1 9h16"/>
                </svg>
                </button>
                <button className="btn btn-circle btn-outline mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 " fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" /></svg>
                </button>
              </td>
              </tr>
              <tr>
              <th>2</th>
              <td>Mahrache Khalil</td>
              <td>+212645547551</td>
              <td>mahrache.khalil@gmail.com</td>
              <td>Y56812</td>
              <td>
                <button className="btn btn-circle btn-outline mr-2">
                <svg className="w-6 h-6 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 18">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 1v16M1 9h16"/>
                </svg>
                </button>
                <button className="btn btn-circle btn-outline mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 " fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" /></svg>
                </button>
              </td>
            </tr>
           
          </tbody>
        </table>
      </div>
    </LayoutDashboard>
  );
}