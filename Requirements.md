My Personal Programming Project Tracker:

Server Application Requirements:
- Keep the server application running continuously.
- Utilize an Executor to manage a fixed thread-pool with 30 connections.
- Maintain a record of all items available in the auction, along with the current highest bid for each item if there is at least one accepted bid.
- Store the IP address of the client who placed an accepted bid.
- Upon a client's attempt to add a new item, return "Failure" if an item with the same name (case sensitive) already exists; otherwise, return "Success".
- When a client submits a bid for an item, return "Failure" if the item doesn't exist on the server or if the bid price is zero or negative.
- Otherwise, return "Accepted" if the new bid surpasses the old bid, and "Rejected" otherwise.
- Create a log file named "log.txt" in the server directory. Record every valid client request in the following format: "date|time|client IP address|request". Exclude any additional output, such as headers, from the log file.

Client Application Requirements:
- Accept one of the provided commands as a command line argument and perform the specified task in coordination with the server.
- The following commands are acceptable:
  - "show": Display a table showing all items in the auction, including columns for item name, current bid, and client IP address if a bid has been accepted.
  - "item <string>": Add a new item to the auction with an initial bid price of zero.
  - "bid <item> <value>": Try to place a bid of <value> for the specified <item>.
  - After each command, the client application should exit.
- The server should listen to a port number between 6000 and 6999. You can choose any port within this range.
- Both the client and the server should be executed on the same machine (localhost).
- Implement communication between the client and server using sockets.
- Use TCP for communication.
- The details of the communication protocol are unspecified; you have the freedom to design any protocol as long as it meets the requirements stated above.
- Neither the client nor the server should require any user interaction after execution. Client instructions must be provided via command line arguments. If an invalid input is detected, the client application should exit and display an informative error message.
- Your code should adhere to the Java coding standards outlined in "JavaCodingStandards.pdf" on Minerva.