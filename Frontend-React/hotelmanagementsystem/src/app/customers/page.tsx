'use client';

import { useState, useEffect } from 'react';
import { QUERY_API_URL } from "@/config";
import Link from 'next/link';
import { CustomerResponseDTO } from '@/types';

export default function CustomersPage() {
    const [customers, setCustomers] = useState<CustomerResponseDTO[]>([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                setError(null);

                const apiUrl = searchTerm
                    ? `${QUERY_API_URL}/getCustomers?name=${encodeURIComponent(searchTerm)}`
                    : `${QUERY_API_URL}/getCustomers`;

                const response = await fetch(apiUrl);

                if (!response.ok) {
                    new Error(`HTTP error! status: ${response.status}`);
                }

                const data = (await response.json()) as CustomerResponseDTO[];
                setCustomers(data);
            } catch (error) {
                console.error('Error fetching customers:', error);
                setError('Failed to load customers. Please try again later.');
            } finally {
                setLoading(false);
            }
        };

        const timer = setTimeout(() => {
            fetchData();
        }, 300);

        return () => clearTimeout(timer);
    }, [searchTerm]);

    const formatDate = (dateString: string) => {
        if (!dateString) return 'N/A';
        const date = new Date(dateString);
        return date.toLocaleDateString();
    };

    return (
        <div className="container mx-auto p-6">
            <div className="flex items-center justify-between mb-6">
                <h1 className="text-2xl font-bold">Customer Management</h1>
                <div className="flex gap-4">
                    <Link href="/customers/new">
                        <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                            New Customer
                        </button>
                    </Link>
                    <Link href="/customers/update">
                        <button className="px-4 py-2 border border-blue-500 text-blue-500 rounded hover:bg-blue-50">
                            Update Customer
                        </button>
                    </Link>
                </div>
            </div>

            {error && (
                <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded">
                    {error}
                </div>
            )}

            <div className="mb-6 bg-white p-4 rounded-lg shadow">
                <label className="block text-sm font-medium text-gray-700 mb-2">Search by Name</label>
                <input
                    type="text"
                    placeholder="Enter customer name..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>

            {loading ? (
                <div className="flex justify-center items-center h-64">
                    <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-blue-500"></div>
                </div>
            ) : (
                <div className="bg-white rounded-lg shadow overflow-hidden">
                    {customers.length === 0 ? (
                        <div className="p-6 text-center text-gray-500">
                            {searchTerm ? 'No customers found matching your search' : 'No customers available'}
                        </div>
                    ) : (
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">First Name</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last Name</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Address</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Birth Date</th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {customers.map((customer) => (
                                    <tr key={customer.id}>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-900">{customer.firstName}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-900">{customer.lastName}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">{customer.email}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">{customer.address}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-gray-500">{formatDate(customer.birthDate)}</td>
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