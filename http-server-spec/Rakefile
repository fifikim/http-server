def is_server_running?(port)
  system("lsof -i:#{port}", out: '/dev/null')
end

def server_not_running_error
  STDERR.puts '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'
  STDERR.puts 'I tried to connect to the server on port 5000.'
  STDERR.puts 'However, I was unable to successfully connect.'
  STDERR.puts 'Maybe check if the server is up and running?'
  STDERR.puts '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'
  exit 1
end

desc "Run all of the tests in the suite"
task :test do
  if is_server_running?(5000)
    puts `bundle exec spinach`
  else
    server_not_running_error()
  end
end

namespace :test do
  desc "Run all of the tests in 01_getting_started"
  task :f1 do
    if is_server_running?(5000)
      puts `bundle exec spinach --tags 01-getting-started`
    else
      server_not_running_error()
    end
  end

  desc "Run all of the tests in 02_structured_data"
  task :f2 do
    if is_server_running?(5000)
      puts `bundle exec spinach --tags 02-structured-data`
    else
      server_not_running_error()
    end
  end

  desc "Run all of the tests in 03_file_server"
  task :f3 do
    if is_server_running?(5000)
      puts `bundle exec spinach --tags 03-file-server`
    else
      server_not_running_error()
    end
  end

  desc "Run all of the tests in 04_todo_list"
  task :f4 do
    if is_server_running?(5000)
      puts `bundle exec spinach --tags 04-todo-list`
    else
      server_not_running_error()
    end
  end
end

desc "Start a demo server with working endpoints"
task :server do
  `ruby server.rb`
end
