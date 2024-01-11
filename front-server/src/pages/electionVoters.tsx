import { useState, ChangeEvent, FormEvent } from "react";
import LayoutDashboard from "../layouts/layout-dashboard";
import { Link } from "react-router-dom";

interface CandidateInfo {
  fullName: string;
  cin: string;
  photo: File | string;
  team: string;
}

export default function ElectionVoters() {
  const [showPopup, setShowPopup] = useState(false);
  const [candidateInfo, setCandidateInfo] = useState<CandidateInfo>({
    fullName: "",
    cin: "",
    photo: "", 
    team: "",
  });
  const [candidates, setCandidates] = useState<CandidateInfo[]>([]);

  const handleInputChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>
  ): void => {
    const { name, files, value, type } = e.target as HTMLInputElement;
  
    if (type === "file" && files) {
      const file = files[0];
      const reader = new FileReader();
      reader.onloadend = () => {
        setCandidateInfo((prevInfo) => ({ ...prevInfo, [name]: reader.result as string }));
      };
      reader.readAsDataURL(file);
    } else {
      setCandidateInfo((prevInfo) => ({ ...prevInfo, [name]: type === 'file' ? '' : value }));
    }
  };

  const handleFormSubmit = (e: FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    setCandidates((prevCandidates) => [...prevCandidates, candidateInfo]);
    setShowPopup(false);
  };
    return (
      <LayoutDashboard>
         <div id="home">
        <div className="lg:flex justify-between items-center mb-6">
          <p className="text-2xl font-semibold mb-2 lg:mb-0">Voters</p>
          <button
            className="bg-blue-500 hover:bg-blue-600 focus:outline-none rounded-lg px-6 py-2 text-white font-semibold shadow"
            onClick={() => setShowPopup(true)}
          >
            Add voters
          </button>
        </div>
      </div>
  
        {/* Popup for creating a new voters */}
        {showPopup && (
          <div
          style={{
            position: 'fixed',
            top: '0',
            left: '0',
            width: '100%',
            height: '100%',
            background: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <div
            className="popup-inner"
            style={{
              background: 'white',
              padding: '20px',
              borderRadius: '8px',
              boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
              width: '500px',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <h2 style={{ marginBottom: '20px' }}>Ad voters</h2>
            <form onSubmit={handleFormSubmit} style={{ width: '100%' }}>
              <div className="form-group m-2">
                <input
                  type="checkbox"
                  className=" input input-bordered input-primary  max-w-xs m-5"
                  id="fullName"
                  name="fullName"
                  placeholder="full name"
                  value={candidateInfo.fullName}
                  onChange={handleInputChange}
                  required
                /> Zamame ayoub
              </div>
              <div className="form-group m-2">
                <input
                  type="checkbox"
                  placeholder="CIN"
                  className=" input input-bordered input-primary  max-w-xs m-5"
                  id="cin"
                  name="cin"
                  value={candidateInfo.cin}
                  onChange={handleInputChange}
                  required
                />Mahrache Khalil
              </div>
              <div className="form-group m-5 btn" style={{ marginTop: '20px' }}>
                <button type="submit">Submit</button>
              </div>
            </form>
            <button
              onClick={() => setShowPopup(false)}
              style={{ marginTop: '10px', cursor: 'pointer' }}
            >
              Close
            </button>
          </div>
        </div>
        )}
  
        {/* Table with voters */}
        {!showPopup && (
        <div className="mt-4 flex flex-col bg-gray-100 rounded-lg p-4 shadow-sm">
          <div className="overflow-x-auto">
            <table className="table">
              <thead>
                <tr>
                  <th>id</th>
                  <th>voter Name</th>
                  <th>email</th>
                  <th>CIN</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                {candidates.map((candidate, index) => (
                  <tr key={index}>
                    <td>#{index + 1}</td>
                    <td>
                      <div className="flex items-center gap-3">
                        <div>
                          <div className="font-bold">{candidate.fullName}</div>
                        </div>
                      </div>
                    </td>
                    <td>{candidate.team}</td>
                    <td>{candidate.cin}</td>
                    <td>
                      <button className="btn btn-ghost btn-xs">details</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
      <Link to={'/elections'}>
            <button className="bg-blue-500 hover:bg-blue-600 focus:outline-none rounded-lg px-6 py-2 text-white font-semibold shadow">
              publish 
            </button>
      </Link>
      </LayoutDashboard>
    );
}
