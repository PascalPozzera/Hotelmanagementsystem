'use client';

import { useState } from "react";
import {ArrowPathIcon} from "@heroicons/react/24/outline";
import {EVENTBUS_API_URL} from "@/config";

export default function ReplayEvents() {
    const [loading, setLoading] = useState(false);

    const replayEvents = async () => {
        try {
            setLoading(true);

            const response = await fetch(EVENTBUS_API_URL+ '/replayEvents', {
                method: 'POST',
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            await response.json();
        } catch (error) {
            console.error('Error replaying events:', error);
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
