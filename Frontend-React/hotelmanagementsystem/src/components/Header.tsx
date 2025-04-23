import Link from "next/link";
import {HomeIcon, CalendarDaysIcon, UserGroupIcon, BuildingOffice2Icon } from "@heroicons/react/24/outline";
import NavLink from "@/components/NavLink";
import ReplayEvents from "@/components/ReplayEvents";

export default function Header() {
    return (
        <header className="border-b border-gray-200 bg-gray-200 mb-10">
            <div className="container mx-auto px-4 py-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-between">
                    <Link href="/" className="flex items-center space-x-2">
                        <HomeIcon className="w-7 h-7" />
                    </Link>

                    <nav className="flex-1 flex justify-center">
                        <NavLink href="/customers">
                            <UserGroupIcon className="w-7 h-7 mr-2" />
                            Customers
                        </NavLink>
                        <NavLink href="/rooms">
                            <BuildingOffice2Icon className="w-7 h-7 mr-2" />
                            Rooms
                        </NavLink>
                        <NavLink href="/bookings">
                            <CalendarDaysIcon className="w-7 h-7 mr-2" />
                            Bookings
                        </NavLink>
                    </nav>

                    <ReplayEvents />

                </div>
            </div>
        </header>
    )
}
