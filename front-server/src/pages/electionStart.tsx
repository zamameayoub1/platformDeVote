import LayoutDashboard from "../layouts/layout-dashboard";

export default function ElectionStart() {
  return (
    <LayoutDashboard>
      <div className="mt-4 flex flex-col bg-gray-100 rounded-lg p-4 shadow-sm">
        <h2 className="ai-story-maker-dream-form text-black font-bold text-2xl">Election form</h2>

        <div className="mt-4">
            <label className="text-black" htmlFor="title">Title</label>
            <input placeholder="Enter a title for your Election" className="w-full bg-white rounded-md border-gray-300 text-black px-2 py-1" type="text"/>
        </div>

        <div className="mt-4">
            <label className="text-black" htmlFor="description">Description</label>
            <textarea placeholder="Describe your election in detail" className="w-full bg-white rounded-md border-gray-300 text-black px-2 py-1" id="description"></textarea>
        </div>

        <div className="mt-4 flex flex-row space-x-2">
            <div className="flex-1">
            <label className="text-black" htmlFor="emotions">Condidat Number</label>
            <input  className="w-full bg-white rounded-md border-gray-300 text-black px-2 py-1" id="emotions" type="number"/>
            </div>
        </div>
        <div className="mt-4 flex flex-row space-x-2">
            <div className="flex-1">
            <label className="text-black" htmlFor="emotions">Voters Number</label>
            <input  className="w-full bg-white rounded-md border-gray-300 text-black px-2 py-1" id="emotions" type="number"/>
            </div>
        </div>
        <p className="mt-2 ">after submiting you will be directed to form of condidats and voters and you will not be able to change the voters number tell the end of election life time</p>
        <div className="mt-4 flex flex-row space-x-2">
            <input  className="w-200 bg-white rounded-md border-gray-300 text-black px-2 py-1 bg-blue-500 hover:bg-blue-600 focus:outline-none rounded-lg px-6 py-2  font-semibold shadow" id="emotions" type="submit"/>
        </div>
        </div>

    </LayoutDashboard>
  );
}
