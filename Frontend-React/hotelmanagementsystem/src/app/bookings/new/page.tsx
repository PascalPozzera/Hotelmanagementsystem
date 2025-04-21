// app/bookings/new/page.tsx
'use client';

import { useSearchParams, useRouter } from 'next/navigation';
import { useState, useEffect } from 'react';
import { COMMAND_API_URL } from '@/config';

export default function NewBookingPage() {
    const searchParams = useSearchParams();
    const router = useRouter();

    const [form, setForm] = useState({
        customerEmail: '',
        numberOfGuests: 1,
        roomNumber: '',
        startDate: '',
        endDate: '',
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        setForm(prev => ({
            ...prev,
            roomNumber: searchParams.get('roomNumber') || '',
            startDate: searchParams.get('startDate') || '',
            endDate: searchParams.get('endDate') || '',
        }));
    }, [searchParams]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({
            ...prev,
            [name]: name === 'numberOfGuests' ? parseInt(value) : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        setSuccess(false);

        const params = new URLSearchParams({
            customerEmail: form.customerEmail,
            numberOfGuests: form.numberOfGuests.toString(),
            roomNumber: form.roomNumber,
            startDate: form.startDate,
            endDate: form.endDate,
        });

        try {
            const response = await fetch(`${COMMAND_API_URL}/bookRoom?${params}`, { method: 'POST' });

            if (!response.ok) throw new Error('Booking failed.');

            setSuccess(true);
            router.push('/bookings');
        } catch (err) {
            console.error(err);
            setError('Failed to book the room. Please check your input.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container mx-auto p-6">
            <h1 className="text-2xl font-bold mb-6">Book Room</h1>

            {error && (
                <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded">
                    {error}
                </div>
            )}

            <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow space-y-4">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                        <label className="block text-sm font-medium mb-1">Room Number</label>
                        <input
                            type="text"
                            name="roomNumber"
                            value={form.roomNumber}
                            required
                            className="w-full p-2 border border-gray-300 rounded"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium mb-1">Number of Guests</label>
                        <input
                            type="number"
                            name="numberOfGuests"
                            min="1"
                            value={form.numberOfGuests}
                            onChange={handleChange}
                            required
                            className="w-full p-2 border border-gray-300 rounded"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium mb-1">Start Date</label>
                        <input
                            type="date"
                            name="startDate"
                            value={form.startDate}
                            onChange={handleChange}
                            required
                            className="w-full p-2 border border-gray-300 rounded"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium mb-1">End Date</label>
                        <input
                            type="date"
                            name="endDate"
                            value={form.endDate}
                            onChange={handleChange}
                            required
                            className="w-full p-2 border border-gray-300 rounded"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium mb-1">Customer Email</label>
                        <input
                            type="email"
                            name="customerEmail"
                            value={form.customerEmail}
                            onChange={handleChange}
                            required
                            className="w-full p-2 border border-gray-300 rounded"
                        />
                    </div>
                </div>

                <div className="pt-4">
                    <button
                        type="submit"
                        disabled={loading}
                        className="w-full md:w-auto px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 disabled:bg-blue-300"
                    >
                        {loading ? 'Booking...' : 'Book Room'}
                    </button>
                </div>
            </form>
        </div>
    );
}
