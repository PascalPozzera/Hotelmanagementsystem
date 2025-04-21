import React, { useState } from 'react';

interface PaymentMethodModalProps {
    isOpen: boolean;
    onClose: () => void;
    onConfirm: (paymentMethod: string) => void;
}

const paymentMethods = [
    "NOT_SELECTED",
    "CASH",
    "CREDIT_CARD",
    "DEBIT_CARD",
    "PAYPAL",
    "APPLE_PAY",
    "GOOGLE_PAY",
    "BANK_TRANSFER",
];

const PaymentMethodModal: React.FC<PaymentMethodModalProps> = ({
                                                                   isOpen,
                                                                   onClose,
                                                                   onConfirm,
                                                               }) => {
    const [selectedMethod, setSelectedMethod] = useState<string>("NOT_SELECTED");

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 flex justify-center items-center z-50 backdrop-blur-sm">
            <div className="bg-white p-6 rounded-md shadow-md max-w-sm w-full z-10">
                <p className="text-sm mb-4 text-gray-700">Please select a payment method:</p>
                <select
                    value={selectedMethod}
                    onChange={(e) => setSelectedMethod(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md mb-4"
                >
                    {paymentMethods.map((method) => (
                        <option key={method} value={method}>
                            {method.replace("_", " ")}
                        </option>
                    ))}
                </select>
                <div className="flex justify-between gap-4">
                    <button
                        onClick={onClose}
                        className="px-4 py-2 bg-gray-300 rounded-md hover:bg-gray-400"
                    >
                        Cancel
                    </button>
                    <button
                        onClick={() => onConfirm(selectedMethod)}
                        className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                    >
                        Confirm
                    </button>
                </div>
            </div>
        </div>
    );
};

export default PaymentMethodModal;
