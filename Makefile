.PHONY: test docs

ifeq ($(VIRTUAL_ENV),)
$(error Please activate virtual environment for Python)
endif

run:
	python ./courier --config ~/tmp/courier/config.toml

test:
	python -m pytest --show-capture=all tests/
