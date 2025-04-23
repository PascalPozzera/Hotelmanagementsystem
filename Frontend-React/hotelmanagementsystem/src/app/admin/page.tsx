"use client";

import { useState } from 'react';
import { QUERY_ADMIN_URL, COMMAND_ADMIN_URL, EVENTBUS_ADMIN_URL } from '@/config';
import type { StoredEventDTO } from "@/types";

type APIResponse = string | StoredEventDTO[];

const API_ENDPOINTS = {
    loadBaseData: COMMAND_ADMIN_URL + '/loadBaseData',
    allEvents: EVENTBUS_ADMIN_URL + '/allEvents',
    replayEvents: EVENTBUS_ADMIN_URL + '/replayEvents',
    reset: QUERY_ADMIN_URL + '/reset'
};

export default function AdminPage() {
    const [loading, setLoading] = useState<Record<string, boolean>>({
        loadBaseData: false,
        reset: false,
        replayEvents: false,
        allEvents: false
    });
    const [message, setMessage] = useState<string | null>(null);
    const [events, setEvents] = useState<StoredEventDTO[]>([]);
    const [error, setError] = useState<string | null>(null);

    const callApi = async (
        endpoint: keyof typeof API_ENDPOINTS,
        method: 'GET' | 'POST' = 'POST'): Promise<APIResponse> => {
        try {
            setLoading(prev => ({ ...prev, [endpoint]: true }));
            setError(null);
            setMessage(null);

            const response = await fetch(API_ENDPOINTS[endpoint], {
                method,
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`API call failed: ${response.statusText}`);
            }

            if (method === 'GET') {
                const data = await response.json() as StoredEventDTO[];
                return data;
            } else {
                const text = await response.text();
                setMessage(text);
                return text;
            }
        } catch (err) {
            setError(err instanceof Error ? err.message : 'An unknown error occurred');
            throw err;
        } finally {
            setLoading(prev => ({ ...prev, [endpoint]: false }));
        }
    };

    const handleLoadBaseData = async () => {
        await callApi('loadBaseData');
    };

    const handleResetAll = async () => {
        await callApi('reset');
    };

    const handleReplayEvents = async () => {
        await callApi('replayEvents');
    };

    const handleGetAllEvents = async () => {
        const events = await callApi('allEvents', 'GET');
        setEvents(events as StoredEventDTO[]);
    };

    return (
        <div>
            <header className="mb-20 text-center">
                <h1 className="text-4xl font-bold text-gray-800">Admin Console</h1>
            </header>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-6xl mx-auto mb-10">
                <div className="bg-white p-6 rounded-2xl shadow-sm hover:shadow-lg transition-shadow border-t-4 border-blue-500">
                    <h2 className="text-2xl font-semibold mb-4 text-gray-800">Data Management</h2>

                    <div className="space-y-4">
                        <button
                            onClick={handleLoadBaseData}
                            disabled={loading.loadBaseData}
                            className={`w-full text-left p-4 rounded-lg transition-colors ${
                                loading.loadBaseData
                                    ? 'bg-blue-200 text-blue-800 cursor-not-allowed'
                                    : 'bg-blue-50 hover:bg-blue-100 text-blue-700'
                            }`}
                        >
                            <div className="flex justify-between items-center">
                                <span>{loading.loadBaseData ? 'Loading Base Data...' : 'Load Base Data'}</span>
                                <span className="text-blue-500 font-medium">→</span>
                            </div>
                        </button>

                        <button
                            onClick={handleResetAll}
                            disabled={loading.reset}
                            className={`w-full text-left p-4 rounded-lg transition-colors ${
                                loading.reset
                                    ? 'bg-red-200 text-red-800 cursor-not-allowed'
                                    : 'bg-red-50 hover:bg-red-100 text-red-700'
                            }`}
                        >
                            <div className="flex justify-between items-center">
                                <span>{loading.reset ? 'Resetting...' : 'Reset All Data'}</span>
                                <span className="text-red-500 font-medium">→</span>
                            </div>
                        </button>
                    </div>
                </div>

                <div className="bg-white p-6 rounded-2xl shadow-sm hover:shadow-lg transition-shadow border-t-4 border-purple-500">
                    <h2 className="text-2xl font-semibold mb-4 text-gray-800">Event Management</h2>

                    <div className="space-y-4">
                        <button
                            onClick={handleReplayEvents}
                            disabled={loading.replayEvents}
                            className={`w-full text-left p-4 rounded-lg transition-colors ${
                                loading.replayEvents
                                    ? 'bg-purple-200 text-purple-800 cursor-not-allowed'
                                    : 'bg-purple-50 hover:bg-purple-100 text-purple-700'
                            }`}
                        >
                            <div className="flex justify-between items-center">
                                <span>{loading.replayEvents ? 'Replaying Events...' : 'Replay All Events'}</span>
                                <span className="text-purple-500 font-medium">→</span>
                            </div>
                        </button>

                        <button
                            onClick={handleGetAllEvents}
                            disabled={loading.allEvents}
                            className={`w-full text-left p-4 rounded-lg transition-colors ${
                                loading.allEvents
                                    ? 'bg-green-200 text-green-800 cursor-not-allowed'
                                    : 'bg-green-50 hover:bg-green-100 text-green-700'
                            }`}
                        >
                            <div className="flex justify-between items-center">
                                <span>{loading.allEvents ? 'Loading Events...' : 'View All Events'}</span>
                                <span className="text-green-500 font-medium">→</span>
                            </div>
                        </button>
                    </div>
                </div>
            </div>

            {error && (
                <div className="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 max-w-6xl mx-auto rounded">
                    <p>{error}</p>
                </div>
            )}

            {message && (
                <div className="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-6 max-w-6xl mx-auto rounded">
                    <p>{message}</p>
                </div>
            )}

            {events.length > 0 && (
                <div className="bg-white p-6 rounded-2xl shadow-sm max-w-6xl mx-auto">
                    <h2 className="text-2xl font-semibold mb-4 text-gray-800">Event Log</h2>
                    <div className="bg-gray-50 p-4 rounded-lg overflow-x-auto">
            <pre className="text-sm text-gray-800">
              {JSON.stringify(events, null, 2)}
            </pre>
                    </div>
                </div>
            )}
        </div>
    );
}