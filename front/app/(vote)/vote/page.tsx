export const metadata = {
  title: 'Vote',
  description: 'Page description',
}




export default function Vote() {
  return (
    <section className="relative">
      <div className="max-w-6xl mx-auto px-4 sm:px-6">
        <div className="pt-32 pb-12 md:pt-40 md:pb-20">

          {/* Page header */}
          <div className="max-w-3xl mx-auto text-center pb-12 md:pb-20">
            <h1 className="h1">Your Voice Matters, Your Vote Counts!</h1>
          </div>

          {/* Form */}
          <div className="max-w-sm mx-auto">
            <form >
              <div className="flex  -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="full-name">Zamame ayoub </label>
                  <input id="zamameayoub-v" name="border-radio"  type="radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                </div>
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="full-name">Mahrache Khalil </label>
                  <input id="zamameayoub-v" name="border-radio"  type="radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                </div>
                
              </div>
              <div className="flex flex-wrap -mx-3 mt-6">
                <div className="w-full px-3">
                  <button className="btn text-white bg-purple-600 hover:bg-purple-700 w-full">Submit</button>
                </div>
              </div>
            </form>
          </div>

        </div>
      </div>
    </section>
  )
}
