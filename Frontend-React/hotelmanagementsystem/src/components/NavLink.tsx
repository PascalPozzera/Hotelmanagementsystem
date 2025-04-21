'use client'

import Link from 'next/link';
import {useSelectedLayoutSegment} from 'next/navigation';

interface NavLinkProps {
    href: string;
    children: React.ReactNode;
}

export default function NavLink({href, children}: NavLinkProps) {
    const segment = useSelectedLayoutSegment();
    const isActive = href === `/${segment}`;

    return (
        <Link
            href={href}
            className={`flex items-center px-3 py-2 rounded-md text-sm transition-colors ${
                isActive
                    ? 'bg-gray-300 text-gray-900 font-bold'
                    : 'text-gray-600 hover:bg-gray-300 hover:text-gray-900'
            }`}
        >
            {children}
        </Link>
    );
}