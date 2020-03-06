.PHONY: test

test:
	bash launcher.sh test/01_input.json > test/01_test_out.txt && \
	echo diff test/01_output.txt test/01_test_out.txt && \
	diff test/01_output.txt test/01_test_out.txt && \
	bash launcher.sh test/02_input.json > test/02_test_out.txt && \
	echo diff test/02_output.txt test/02_test_out.txt && \
	diff test/02_output.txt test/02_test_out.txt
