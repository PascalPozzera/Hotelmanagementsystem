'use client';

import { useState, useEffect } from 'react';
import { QUERY_API_URL } from "@/config";
import {COMMAND_API_URL} from "@/config";
import { BookingResponseDTO } from '@/types';
import CancelConfirmationModal from "@/components/CancelConfirmationModal";
import PaymentMethodModal from "@/components/PaymentMethodModal";

export default function BookingsPage() {
    const [bookings, setBookings] = useState<BookingResponseDTO[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [dateRange, setDateRange] = useState({
        from: '',
        to: '',
    });
    const [showCancelConfirmationModal, setShowCancelConfirmationModal] = useState(false);
    const [showPaymentMethodModal, setShowPaymentMethodModal] = useState(false);
    const [selectedBooking, setSelectedBooking] = useState<BookingResponseDTO | null>(null);

    const fetchBookings = async () => {
        try {
            setLoading(true);
            setError(null);

            const params = new URLSearchParams();
            if (dateRange.from) params.append('from', dateRange.from);
            if (dateRange.to) params.append('to', dateRange.to);

            const apiUrl = `${QUERY_API_URL}/getBookings${params.toString() ? `?${params.toString()}` : ''}`;

            const response = await fetch(apiUrl);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json() as BookingResponseDTO[];
            setBookings(data);
        } catch (error) {
            console.error('Error fetching bookings:', error);
            setError('Failed to load bookings. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        const timer = setTimeout(() => {
            fetchBookings();
        }, 300);

        return () => clearTimeout(timer);
    }, [dateRange.from, dateRange.to]);

    const handleReset = () => {
        setDateRange({from: '', to: ''});
    };

    const handleCancel = async (booking: BookingResponseDTO) => {
        setSelectedBooking(booking);
        setShowCancelConfirmationModal(true);
    };

    const handleConfirmCancel = async () => {
        if (!selectedBooking) return;

        try {
            const query = new URLSearchParams({
                bookingId: selectedBooking.bookingId,
                customerEmail: selectedBooking.email,
                roomNumber: selectedBooking.roomNumber.toString(),
            });

            const response = await fetch(`${COMMAND_API_URL}/cancelBooking?${query.toString()}`, {
                method: 'POST',
            });

            if (!response.ok) throw new Error("Cancel failed");

            fetchBookings();
            setShowCancelConfirmationModal(false);
        } catch (err) {
            console.error(err);
            alert("Failed to cancel booking.");
        }
    };

    const handlePay = async (booking: BookingResponseDTO) => {
        setSelectedBooking(booking);
        setShowPaymentMethodModal(true);
    };

    const handleConfirmPay = async (paymentMethod: string) => {
        if (!selectedBooking) return;

        try {
            const query = new URLSearchParams({
                bookingId: selectedBooking.bookingId,
                customerEmail: selectedBooking.email,
                roomNumber: selectedBooking.roomNumber.toString(),
                paymentMethod: paymentMethod.toUpperCase(),
            });

            const response = await fetch(`${COMMAND_API_URL}/payBooking?${query.toString()}`, {
                method: 'POST',
            });

            if (!response.ok) throw new Error("Payment failed");

            fetchBookings();
            setShowPaymentMethodModal(false);
        } catch (err) {
            console.error(err);
            alert("Failed to complete payment.");
        }
    };

    return (
        <div className="container mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Booking Management</h1>

            {error && (
                <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded">
                    {error}
                </div>
            )}

            <div className="mb-6 bg-white p-4 rounded-lg shadow">
                <div className="flex flex-col md:flex-row md:items-end gap-4 w-full">
                    <div className="flex-1">
                        <label className="block text-sm font-medium text-gray-700 mb-2">From Date</label>
                        <input
                            type="date"
                            value={dateRange.from}
                            onChange={(e) => setDateRange({ ...dateRange, from: e.target.value })}
                            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                    <div className="flex-1">
                        <label className="block text-sm font-medium text-gray-700 mb-2">To Date</label>
                        <input
                            type="date"
                            value={dateRange.to}
                            onChange={(e) => setDateRange({ ...dateRange, to: e.target.value })}
                            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                    <div className="flex gap-2 md:ml-auto w-full md:w-auto">
                        <button
                            onClick={fetchBookings}
                            className="w-full md:w-auto px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition"
                            disabled={loading}
                        >
                            {loading ? 'Loading...' : 'Search'}
                        </button>
                        <button
                            onClick={handleReset}
                            className={`w-full md:w-auto px-4 py-2 rounded-md transition ${
                                dateRange.from || dateRange.to
                                    ? 'bg-gray-100 text-gray-700 hover:bg-gray-200'
                                    : 'bg-gray-50 text-gray-400 cursor-not-allowed'
                            }`}
                            disabled={!dateRange.from && !dateRange.to}
                        >
                            Clear
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
                    {bookings.length === 0 ? (
                        <div className="p-6 text-center text-gray-500">
                            {dateRange.from || dateRange.to ? 'No bookings found for selected range' : 'No bookings available'}
                        </div>
                    ) : (
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Room</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Customer</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Dates</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Guests</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Payment Method</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Paid</th>
                                    <th className="text-right text-gray-500"></th>
                                    <th className="text-right text-gray-500"></th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {bookings.map((booking) => (
                                    <tr key={booking.bookingId}>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-900">Room {booking.roomNumber}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">{booking.email}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">
                                            {new Date(booking.startDate).toLocaleDateString()} – {new Date(booking.endDate).toLocaleDateString()}
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">{booking.numberOfGuests}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500 capitalize">{booking.paymentMethod.replace('_', ' ')}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <span className={`px-2 py-1 text-xs rounded-full ${
                                                booking.isPayed ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                                            }`}>
                                                {booking.isPayed ? 'Paid' : 'Unpaid'}
                                            </span>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap text-right space-x-2">
                                            <button
                                                onClick={() => handleCancel(booking)}
                                                className="px-3 py-1 text-sm bg-red-500 text-white rounded hover:bg-red-600"
                                            >
                                                Cancel
                                            </button>
                                            <button
                                                onClick={() => handlePay(booking)}
                                                className="px-3 py-1 text-sm bg-green-500 text-white rounded hover:bg-green-600 disabled:opacity-50"
                                                disabled={booking.isPayed}
                                            >
                                                Pay
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>

                            </table>
                        </div>
                    )}
                </div>
            )}
            {/* Cancel Confirmation Modal */}
            <CancelConfirmationModal
                isOpen={showCancelConfirmationModal}
                onClose={() => setShowCancelConfirmationModal(false)}
                onConfirm={handleConfirmCancel}
                message={`Are you sure you want to cancel the booking for room ${selectedBooking?.roomNumber}?`}
            />

            {/* Payment Method Modal */}
            <PaymentMethodModal
                isOpen={showPaymentMethodModal}
                onClose={() => setShowPaymentMethodModal(false)}
                onConfirm={handleConfirmPay}
            />
        </div>
    );
}
