# ATM System

A console-based ATM (Automated Teller Machine) system implemented in Java that simulates basic banking operations.

## Features

- Secure login with User ID and PIN
- View transaction history with timestamps
- Withdraw funds
- Deposit funds
- Transfer money between accounts
- Real-time balance tracking
- Transaction logging

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, etc.) or command line compiler

### Running the Application

1. Compile the Java file:
```bash
javac ATM.java
```

2. Run the compiled program:
```bash
java ATM
```

### Default Accounts

The system comes with two pre-configured accounts for testing:

1. Account 1:
   - User ID: user1
   - PIN: 1234
   - Initial Balance: $1000.00

2. Account 2:
   - User ID: user2
   - PIN: 5678
   - Initial Balance: $2000.00

## Usage

1. **Login**
   - Enter your User ID and PIN when prompted
   - System will validate your credentials

2. **Main Menu**
   After successful login, you can perform the following operations:
   1. View Transaction History
   2. Withdraw
   3. Deposit
   4. Transfer
   5. Quit

3. **Transaction History**
   - Shows all transactions with type, amount, and timestamp
   - Displays "No transaction history available" if no transactions exist

4. **Withdraw**
   - Enter the amount to withdraw
   - System checks for sufficient balance
   - Updates balance and transaction history upon successful withdrawal

5. **Deposit**
   - Enter the amount to deposit
   - Updates balance and transaction history automatically

6. **Transfer**
   - Enter recipient's User ID
   - Enter amount to transfer
   - System validates recipient and sufficient balance
   - Updates both accounts and transaction histories

7. **Quit**
   - Safely exits the application
   - Returns to login prompt

## Technical Details

The project consists of three main classes:

1. `Transaction`: Manages individual transaction records
2. `Account`: Handles account-related operations and data
3. `ATM`: Main class containing the user interface and program logic

## Security Features

- PIN verification for account access
- Balance validation for withdrawals and transfers
- Transaction logging with timestamps

## Error Handling

- Invalid login credentials
- Insufficient funds
- Invalid menu choices
- Non-existent recipient accounts

## Contributing

Feel free to fork this project and submit pull requests with improvements.

## License

This project is open source and available under the MIT License.
