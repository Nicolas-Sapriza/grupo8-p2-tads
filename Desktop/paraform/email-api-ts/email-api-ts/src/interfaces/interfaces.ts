
export interface ProspeoResponse {
    response: {
        free: boolean;
        email: string;
        email_anon_id: string;
        email_status: string;
        first_name: string;
        last_name: string;
        domain: string;
        total_emails: number;
    }
}


export interface LeadMagicResponse {
    status: 'valid' | 'not_found'; 
    credits_consumed: number;
    message: string;
    email?: string; 
    first_name: string;
    last_name: string;
    domain: string;
    is_domain_catch_all: boolean;
    mx_record: string;
    mx_provider: string;
    mx_security_gateway: boolean;
    company_name: string;
    company_industry: string;
    company_size: string;
    company_founded: number;
    company_location: {
        name?: string;
        locality?: string;
        region?: string;
        metro?: string;
        country?: string;
        continent?: string;
        street_address?: string;
        address_line_2?: string | null;
        postal_code?: string;
        geo?: string;
    };
    company_linkedin_url: string;
    company_linkedin_id: string;
    company_facebook_url: string;
    company_twitter_url: string;
    company_type: string;
}

export interface HunterResponse {
    data: {
        first_name: string;
        last_name: string;
        email: string;
        score: number;
        domain: string;
        accept_all: boolean;
        position: string;
        twitter: string | null;
        linkedin_url: string | null;
        phone_number: string | null;
        company: string;
        sources: Array<{
            domain: string;
            uri: string;
            extracted_on: string;
            last_seen_on: string;
            still_on_page: boolean;
        }>;
        verification: {
            date: string;
            status: string;
        };
    };
    meta: {
        params: {
            first_name: string;
            last_name: string;
            full_name: string | null;
            domain: string;
            company: string | null;
            max_duration: string | null;
        };
    };
}


export interface FindyMailResponse {
    contact?: {
        name: string;
        domain: string;
        email: string;
    };
    error?: string;
}

export interface DatagmaResponse {
    email: string;
    emailDomain: string;
    status: string;
    patterns: string[];
    mxfound: boolean;
    smtpCheck: boolean;
    cachAll: boolean;
}