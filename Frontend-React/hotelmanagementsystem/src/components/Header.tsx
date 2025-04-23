import {HomeIcon, CalendarDaysIcon, UserGroupIcon, BuildingOffice2Icon } from "@heroicons/react/24/outline";
import { ShieldCheckIcon} from "@heroicons/react/16/solid";
import NavLink from "@/components/NavLink";

export default function Header() {
    return (
        <header className="border-b border-gray-200 bg-gray-200 mb-10">
            <div className="container mx-auto px-4 py-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-between">
                    <NavLink href="/">
                        <HomeIcon className="w-7 h-7" />
                    </NavLink>

                    <nav className="flex justify-center space-x-4">
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

                    <div className="flex justify-end">
                        <NavLink href="/admin">
                            <ShieldCheckIcon className="w-7 h-7 mr-2" />
                            Admin
                        </NavLink>
                    </div>
                </div>
            </div>
        </header>
    )
}
