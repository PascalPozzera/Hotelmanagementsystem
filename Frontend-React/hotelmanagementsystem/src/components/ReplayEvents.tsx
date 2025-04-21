'use client';

import { useState } from "react";
import {ArrowPathIcon} from "@heroicons/react/24/outline";
import {EVENTBUS_API_URL} from "@/config";

export default function ReplayEvents() {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState<boolean | null>(null);

    const replayEvents = async () => {
        try {
            setLoading(true);
            setError(null);
            setSuccess(null);

            const response = await fetch(EVENTBUS_API_URL+ '/replayEvents', {
                method: 'POST',
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            setSuccess(true);
        } catch (error) {
            console.error('Error replaying events:', error);
            setError('Failed to replay events. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <button onClick={replayEvents} disabled={loading} className="flex items-center px-3 py-2 rounded-md text-sm transition-colors text-gray-600 hover:bg-gray-300 hover:text-gray-900">
                <ArrowPathIcon className="w-7 h-7 mr-2" />
                <span>{loading ? 'Replaying...' : 'Replay Events'}</span>
            </button>
        </div>
    );
}
