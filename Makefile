.PHONY: test docs

ifeq ($(VIRTUAL_ENV),)
$(error Please activate virtual environment for Python)
endif

test:
	python -m pytest --show-capture=all tests/
