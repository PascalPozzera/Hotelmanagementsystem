'use client';

import { useState, useEffect } from 'react';
import Link from 'next/link';
import { QUERY_API_URL } from "@/config";
import { RoomResponseDTO } from '@/types';

export default function RoomAvailability() {
    const [rooms, setRooms] = useState<RoomResponseDTO[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [dateRange, setDateRange] = useState({
        start: new Date(),
        end: new Date(new Date().setDate(new Date().getDate() + 1)),
    });

    const formatDateForAPI = (date: Date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };

    const fetchRooms = async () => {
        try {
            setLoading(true);
            setError(null);

            const startDate = formatDateForAPI(dateRange.start);
            const endDate = formatDateForAPI(dateRange.end);

            const response = await fetch(
                `${QUERY_API_URL}/getFreeRooms?startDate=${startDate}&endDate=${endDate}`
            );

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json() as RoomResponseDTO[];
            setRooms(data);
        } catch (error) {
            console.error('Error fetching rooms:', error);
            setError('Failed to load room availability. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchRooms();
    }, [dateRange.start, dateRange.end]);

    return (
        <div className="container mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Room Availability</h1>

            {error && (
                <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded">
                    {error}
                </div>
            )}

            <div className="mb-6 bg-white p-4 rounded-lg shadow">
                <div className="flex flex-col md:flex-row md:items-end gap-4 w-full">
                    <div className="flex-1">
                        <label className="block text-sm font-medium text-gray-700 mb-2">From</label>
                        <input
                            type="date"
                            value={formatDateForAPI(dateRange.start)}
                            onChange={(e) =>
                                setDateRange((prev) => ({ ...prev, start: new Date(e.target.value) }))
                            }
                            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            min={formatDateForAPI(new Date())}
                        />
                    </div>
                    <div className="flex-1">
                        <label className="block text-sm font-medium text-gray-700 mb-2">To</label>
                        <input
                            type="date"
                            value={formatDateForAPI(dateRange.end)}
                            onChange={(e) =>
                                setDateRange((prev) => ({ ...prev, end: new Date(e.target.value) }))
                            }
                            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            min={formatDateForAPI(new Date(dateRange.start.getTime() + 86400000))}
                        />
                    </div>
                    <div className="flex gap-2 md:ml-auto w-full md:w-auto">
                        <button
                            onClick={fetchRooms}
                            disabled={loading}
                            className="w-full md:w-auto px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:bg-blue-300"
                        >
                            {loading ? 'Searching...' : 'Search'}
                        </button>
                    </div>
                </div>
            </div>


            {loading ? (
                <div className="flex justify-center items-center h-64">
                    <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-blue-500"></div>
                </div>
            ) : (
                <div className="bg-white rounded-lg shadow overflow-hidden">
                    {rooms.length === 0 ? (
                        <div className="p-6 text-center text-gray-500">
                            No rooms available for the selected dates
                        </div>
                    ) : (
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Room Number</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Type</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Price</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Guests</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Balcony</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Description</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase"></th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {rooms.map((room) => (
                                    <tr key={room.roomId}>
                                        <td className="px-6 py-4">{room.roomNumber}</td>
                                        <td className="px-6 py-4 capitalize">{room.roomType}</td>
                                        <td className="px-6 py-4">${room.roomPrice}</td>
                                        <td className="px-6 py-4">{room.numberOfPerson}</td>
                                        <td className="px-6 py-4">{room.withBalcony ? 'Yes' : 'No'}</td>
                                        <td className="px-6 py-4">{room.description || 'N/A'}</td>
                                        <td className="px-6 py-4 text-right">
                                            <Link
                                                href={{
                                                    pathname: '/bookings/new',
                                                    query: {
                                                        roomNumber: room.roomNumber,
                                                        startDate: formatDateForAPI(dateRange.start),
                                                        endDate: formatDateForAPI(dateRange.end),
                                                    },
                                                }}
                                            >
                                                <button className="px-4 py-2 border border-blue-500 text-blue-500 rounded hover:bg-blue-50">
                                                    Book
                                                </button>
                                            </Link>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}
