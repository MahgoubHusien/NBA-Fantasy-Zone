import csv

# Define the input and output file paths
input_file = 'nicknames.txt'
output_file = 'nicknames.csv'

# Open the input file in read mode
with open(input_file, 'r') as txt_file:
    # Create a CSV reader object
    csv_reader = csv.reader(txt_file)

    # Open the output file in write mode
    with open(output_file, 'w', newline='') as csv_file:
        # Create a CSV writer object
        csv_writer = csv.writer(csv_file)

        # Iterate over the rows in the .txt file and write to the CSV file
        for row in csv_reader:
            csv_writer.writerow(row)
