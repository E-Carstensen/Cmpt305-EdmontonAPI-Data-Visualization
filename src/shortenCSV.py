import csv

def copy_first_n_lines(input_file_path, output_file_path, n):
    try:
        with open(input_file_path, 'r', newline='') as infile, open(output_file_path, 'w', newline='') as outfile:
            reader = csv.reader(infile)
            writer = csv.writer(outfile)
            for i, row in enumerate(reader):
                if i < n:
                    writer.writerow(row)
                else:
                    break
        print(f"{n} lines copied from '{input_file_path}' to '{output_file_path}'.")
    except FileNotFoundError:
        print(f"The file '{input_file_path}' was not found.")
    except Exception as e:
        print(f"An error occurred: {str(e)}")

input_file_path = 'data.csv'  # Replace with your input CSV file path
output_file_path = 'test.csv'  # Replace with the desired output CSV file path
n = 100  # Replace with the number of lines you want to copy
copy_first_n_lines(input_file_path, output_file_path, n)
