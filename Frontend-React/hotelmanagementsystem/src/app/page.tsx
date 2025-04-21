import Link from "next/link";

export default function Dashboard() {
  return (
      <div>
        <header className="mb-20 text-center">
          <h1 className="text-4xl font-bold text-gray-800">Hotel Management Dashboard</h1>
        </header>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 max-w-6xl mx-auto">
          <Link
              href="/customers"
              className="bg-white p-6 rounded-2xl shadow-sm hover:shadow-lg transition-shadow border-t-4 border-blue-500 hover:border-blue-600"
          >
            <h2 className="text-2xl font-semibold mb-2 text-gray-800">Customers</h2>
            <p className="text-gray-600 mb-4">View and search all customer records</p>
            <div className="text-blue-600 font-medium">View Customers →</div>
          </Link>

          <Link
              href="/rooms"
              className="bg-white p-6 rounded-2xl shadow-sm hover:shadow-lg transition-shadow border-t-4 border-green-500 hover:border-green-600"
          >
            <h2 className="text-2xl font-semibold mb-2 text-gray-800">Room Availability</h2>
            <p className="text-gray-600 mb-4">Check room availability by date range</p>
            <div className="text-green-600 font-medium">Check Rooms →</div>
          </Link>

          <Link
              href="/bookings"
              className="bg-white p-6 rounded-2xl shadow-sm hover:shadow-lg transition-shadow border-t-4 border-purple-500 hover:border-purple-600"
          >
            <h2 className="text-2xl font-semibold mb-2 text-gray-800">Bookings</h2>
            <p className="text-gray-600 mb-4">View and filter bookings by date</p>
            <div className="text-purple-600 font-medium">View Bookings →</div>
          </Link>
        </div>
      </div>
  );
}
