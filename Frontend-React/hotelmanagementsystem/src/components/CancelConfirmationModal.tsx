import React from 'react';

interface CancelConfirmationModalProps {
    isOpen: boolean;
    onClose: () => void;
    onConfirm: () => void;
    message: string;
}

const CancelConfirmationModal: React.FC<CancelConfirmationModalProps> = ({
                                                                             isOpen,
                                                                             onClose,
                                                                             onConfirm,
                                                                             message,
                                                                         }) => {
    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 flex justify-center items-center z-50 backdrop-blur-sm">
            <div className="bg-white p-6 rounded-md shadow-md max-w-sm w-full z-10">
                <p className="text-sm mb-4 text-gray-700">{message}</p>
                <div className="flex justify-between gap-4">
                    <button
                        onClick={onClose}
                        className="px-4 py-2 bg-gray-300 rounded-md hover:bg-gray-400"
                    >
                        No
                    </button>
                    <button
                        onClick={onConfirm}
                        className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600"
                    >
                        Yes, Cancel
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CancelConfirmationModal;
