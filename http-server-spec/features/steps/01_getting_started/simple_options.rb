require "./features/steps/shared.rb"

class Spinach::Features::SimpleOptions < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make an OPTIONS request to "/method_options"' do
    @response = Requests.options("/method_options")
  end

  step 'I make an OPTIONS request to "/method_options2"' do
    @response = Requests.options("/method_options2")
  end

  step 'my response should have allowed headers of GET, HEAD, and OPTIONS' do
    expect(@response.allowed_headers).to contain_exactly("GET", "HEAD", "OPTIONS")
  end

  step 'my response should have allowed headers of GET, HEAD, OPTIONS, PUT, and POST' do
    expect(@response.allowed_headers).to contain_exactly("GET", "HEAD", "OPTIONS", "PUT", "POST")
  end
end
